"use server";

import { api } from "@/lib/api";
import { validateSchema } from "@/lib/validation";
import { redirectWithToast } from "@/server/utils.server";
import { z } from "zod";

export async function singup(_previousState: unknown, formData: FormData) {
	const schema = z
		.object({
			nome: z
				.string({
					message: "O nome é obrigatório.",
				})
				.regex(/^[a-zA-ZÀ-ÿ]{3,}(?: [a-zA-ZÀ-ÿ]{3,})+$/, {
					message:
						"O nome deve ser completo e com pelo menos dois nomes de 3 caracteres.",
				}),
			cpf: z
				.string({
					message: "O CPF é obrigatório.",
				})
				.length(11, "CPF deve conter 11 caracteres."),
			genero: z
				.string({
					message: "O gênero é obrigatório.",
				})
				.refine(
					(val) => {
						return !isNaN(Number(val)) && val.trim() !== "";
					},
					{
						message: "O gênero é obrigatório.",
					},
				)
				.transform((val) => Number(val)),
			email: z
				.string({
					message: "O e-mail é obrigatório.",
				})
				.email({
					message: "E-mail no formato inválido.",
				}),
			telefone: z
				.string({
					message: "O telefone é obrigatório.",
				})
				.regex(/^\d{11}$/, {
					message:
						"Telefone no formato inválido. Deve estar no formato 11912345678.",
				}),
			senha: z
				.string({
					message: "O campo senha é obrigatório.",
				})
				.min(8, "A senha deve conter no mínimo 8 caracteres.")
				.regex(/.*[a-z].*/, {
					message:
						"A senha deve conter pelo menos uma letra minúscula.",
				})
				.regex(/.*[A-Z].*/, {
					message:
						"A senha deve conter pelo menos uma letra maiúscula.",
				})
				.regex(/.*\d.*/, {
					message: "A senha deve conter pelo menos um número.",
				})
				.regex(/.*[\W_].*/, {
					message:
						"A senha deve conter pelo menos um caractere especial.",
				}),
			confirmarSenha: z.string({
				message: "O campo confirmar senha é obrigatório.",
			}),
		})
		.refine((data) => data.senha === data.confirmarSenha, {
			message: "Os campos senha e confirmar senha devem ser iguais.",
			path: ["confirmarSenha", "senha"],
		});

	const data = {
		nome: formData.get("nome"),
		cpf: formData.get("cpf"),
		genero: formData.get("genero"),
		email: formData.get("email"),
		telefone: formData.get("telefone"),
		senha: formData.get("senha"),
		confirmarSenha: formData.get("confirmarSenha"),
	};

	const result = validateSchema(schema, data);

	if (!result.success) {
		return {
			validation: result.error,
			previousState: {
				...data,
			},
		};
	}

	const { error } = await api.POST("/cliente", {
		body: {
			nome: result.data.nome,
			cpf: result.data.cpf,
			email: result.data.email,
			telefone: result.data.telefone,
			senha: result.data.senha,
			generoId: result.data.genero,
		},
	});

	if (error) {
		await redirectWithToast("/cadastro", {
			type: "error",
			title: error.message as string,
			description: error.message as string,
		});
	}

	await redirectWithToast("/", {
		type: "success",
		title: "Sucesso!",
		description: "Cadastro realizado com sucesso.",
	});
}
