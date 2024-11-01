import * as React from "react";

import { cn } from "@/lib/utils";
import { Eye, EyeOff } from "lucide-react";
import { Button } from "./button";

const Input = React.forwardRef<
	HTMLInputElement,
	React.InputHTMLAttributes<HTMLInputElement>
>(({ className, type, ...props }, ref) => {
	const [password, setPassword] =
		React.useState<React.InputHTMLAttributes<HTMLInputElement>["type"]>(
			"password",
		);

	const handlePassword = () =>
		setPassword(password === "password" ? "text" : "password");

	return (
		<div className="relative">
			<input
				type={type === "password" ? password : type}
				className={cn(
					"flex h-10 w-full rounded-md border border-input bg-transparent px-3 py-1 text-sm shadow-sm transition-colors file:border-0 file:bg-transparent file:text-sm file:font-medium file:text-foreground placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-1 focus-visible:ring-ring disabled:cursor-not-allowed disabled:opacity-50",
					className,
				)}
				ref={ref}
				{...props}
			/>

			{type === "password" && (
				<Button
					type="button"
					size="icon"
					className="absolute right-0 top-0"
					variant="ghost"
					onClick={handlePassword}
				>
					{password === "password" ? (
						<Eye size={20} />
					) : (
						<EyeOff size={20} />
					)}
				</Button>
			)}
		</div>
	);
});
Input.displayName = "Input";

export { Input };
