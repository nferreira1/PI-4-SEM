"use client";

import { Label as UILabel } from "@/components/ui/label";
import React, { createContext, useContext, useId } from "react";

type FormFieldContextValue = {
	id: string;
	name?: string;
	error?: string[];
};

const FormFieldContext = createContext<FormFieldContextValue | undefined>(
	undefined,
);

const useFormFieldContext = () => {
	const context = useContext(FormFieldContext);
	if (!context) {
		throw new Error("useFormFieldContext must be used within a FormField");
	}
	return context;
};

export function FormField({
	label,
	error,
	children,
	...props
}: React.HtmlHTMLAttributes<HTMLDivElement> & {
	label: string;
	error?: string[];
	children: React.ReactElement;
}) {
	const id = useId();
	const childName = (
		children.props as React.InputHTMLAttributes<HTMLInputElement>
	).name;

	return (
		<FormFieldContext.Provider value={{ id, name: childName, error }}>
			<div {...props}>
				<div className="space-y-1.5">
					<FormLabel>{label}</FormLabel>
					<FormControl>{children}</FormControl>
				</div>
				<FormMessage />
			</div>
		</FormFieldContext.Provider>
	);
}

export function FormLabel({ children }: { children: React.ReactNode }) {
	const { id } = useFormFieldContext();
	return <UILabel htmlFor={id}>{children}</UILabel>;
}

export function FormControl({ children }: { children: React.ReactNode }) {
	const { id, error } = useFormFieldContext();

	return React.cloneElement(children as React.ReactElement, {
		id,
		"aria-describedby": error ? `${id}-error` : undefined,
		"aria-invalid": !!error,
		className: `data-[error=true]:border-destructive ${
			(children as React.ReactElement).props.className || ""
		}`,
	});
}

export function FormMessage() {
	const { error } = useFormFieldContext();
	if (!error || error.length === 0) return null;

	return (
		<div>
			{error.length > 1 ? (
				<>
					<p className="text-[0.8rem] font-medium text-destructive">
						O campo deve atender aos seguintes requisitos:
					</p>
					<ul
						aria-live="polite"
						className="ml-2 list-inside list-disc text-xs font-medium text-destructive"
					>
						{error.map((err: string, index: number) => (
							<li key={index}>{err}</li>
						))}
					</ul>
				</>
			) : (
				<p
					aria-live="polite"
					className="text-[0.8rem] font-medium text-destructive"
				>
					{error[0]}
				</p>
			)}
		</div>
	);
}

export function FormDescription({ children }: { children: React.ReactNode }) {
	const { id } = useFormFieldContext();
	return (
		<p
			id={`${id}-description`}
			className="text-[0.8rem] text-muted-foreground"
		>
			{children}
		</p>
	);
}
