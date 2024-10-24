import { useEventListener } from "@/hooks/useEventListener";
import { useRef, useState } from "react";

export function useHover<T extends HTMLElement = HTMLElement>() {
	const ref = useRef<T>(null);
	const [value, setValue] = useState<boolean>(false);

	const handleMouseEnter = () => setValue(true);
	const handleMouseLeave = () => setValue(false);

	useEventListener("mouseenter", handleMouseEnter, ref);
	useEventListener("mouseleave", handleMouseLeave, ref);

	return { ref, value };
}
