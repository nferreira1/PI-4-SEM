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

export function capitalizeFirstLetter(string: string | undefined) {
	if (!string) return;
	return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase();
}
