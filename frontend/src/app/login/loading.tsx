import { Skeleton } from "@/components/ui/skeleton";

export default function Loading() {
	return (
		<div className="grid w-[350px] gap-5">
			<div className="grid place-items-center gap-4 pb-4 text-center">
				<Skeleton className="h-9 w-20" />
				<Skeleton className="h-4 w-full" />
			</div>

			<div className="space-y-3">
				<Skeleton className="h-4 w-12" />
				<Skeleton className="h-10 w-full" />
			</div>

			<div className="space-y-3">
				<Skeleton className="h-4 w-12" />
				<Skeleton className="h-10 w-full" />
			</div>

			<div className="grid place-items-center gap-y-8">
				<Skeleton className="h-10 w-full" />
				<Skeleton className="h-4 w-64" />
			</div>
		</div>
	);
}
