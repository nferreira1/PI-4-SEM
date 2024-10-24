import { useEventListener } from "@/hooks/useEventListener";
import { useRef, useState } from "react";

export function useMouse<T extends HTMLElement = HTMLElement>() {
	const ref = useRef<T>(null);
	const [position, setPosition] = useState<{
		readonly x: number;
		readonly y: number;
	}>({ x: 0, y: 0 });
	const [isHovering, setIsHovering] = useState<boolean>(false);

	const handleMouseMove = (event: MouseEvent) => {
		if (!ref.current) return;
		const { left, top } = ref.current.getBoundingClientRect();
		const x = event.pageX - left;
		const y = event.pageY - top;
		setPosition({ x, y });
	};

	const handleMouseEnter = () => setIsHovering(true);
	const handleMouseLeave = () => setIsHovering(false);

	useEventListener("mousemove", handleMouseMove, ref);
	useEventListener("mouseenter", handleMouseEnter, ref);
	useEventListener("mouseleave", handleMouseLeave, ref);

	return { ref, position, isHovering };
}
