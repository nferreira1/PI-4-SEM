import { clsx, type ClassValue } from "clsx";
import { twMerge } from "tailwind-merge";

export function cn(...inputs: ClassValue[]) {
	return twMerge(clsx(inputs));
}

export function formatNumber(number: number) {
	return new Intl.NumberFormat("pt-BR", {
		style: "currency",
		currency: "BRL",
	}).format(number);
}
