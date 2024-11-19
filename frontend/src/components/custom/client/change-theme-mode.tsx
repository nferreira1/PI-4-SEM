"use client";

import { Button } from "@/components/ui/button";
import { Moon, Sun } from "lucide-react";
import { useTheme } from "next-themes";

export function ChangeThemeMode() {
	const { theme, setTheme } = useTheme();

	return (
		<Button
			size="icon"
			variant="outline"
			onClick={() => setTheme(theme === "dark" ? "light" : "dark")}
		>
			{theme === "dark" ? <Sun size={18} /> : <Moon size={18} />}
		</Button>
	);
}
