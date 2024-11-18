import { useEffect } from "react";

/**
 * O hook useEffect não aceita funções async como argumento, por isso esse hook foi criado
 *
 * @param effect Função async que será executada. Normalmente, você deve usar o hook useLoader antes e depois do trecho de código assíncrono
 * @param inputs Array de dependências
 */
export function useEffectAsync<T>(effect: () => void, inputs: T[]) {
	useEffect(() => {
		effect();
	}, inputs);
}
