"use client";

import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Eye, EyeOff } from "lucide-react";
import React, { useId, useState } from "react";

export function FormField({
	label,
	type,
	required,
	...props
}: React.InputHTMLAttributes<HTMLInputElement> & {
	label: string;
	type: React.InputHTMLAttributes<HTMLInputElement>["type"];
	required?: boolean;
}) {
	const [password, setPassword] = useState<typeof type>("password");
	const id = useId();

	if (type === "password") {
		const handlePassword = () =>
			setPassword((prev) => (prev === "password" ? "text" : "password"));

		return (
			<div className="grid gap-2">
				<Label htmlFor={id}>
					{label} {required && "*"}
				</Label>
				<div className="relative flex items-center justify-end">
					<Input id={id} type={password} placeholder="••••••••" />
					<Button
						type="button"
						size="icon"
						variant="ghost"
						className="absolute right-0"
						onClick={handlePassword}
					>
						{password === "password" ? (
							<Eye size={20} />
						) : (
							<EyeOff size={20} />
						)}
					</Button>
				</div>
			</div>
		);
	}

	return (
		<div className="grid gap-2">
			<Label htmlFor={id}>
				{label} {required && "*"}
			</Label>
			<Input id={id} required={required} {...props} />
		</div>
	);
}
