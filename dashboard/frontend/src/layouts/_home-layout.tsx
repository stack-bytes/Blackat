import { ReactNode } from "react"
import Navbar from "../components/Navbar"

export default function HomeLayout({ children }: { children: ReactNode }) {
    return (
        <div className="min-h-screen min-w-screen flex flex-col bg-base-100 p-5">
            <Navbar />
            <div className="badge badge-error  badge-outline gap-2 h-8">
                <svg
                    xmlns="http://www.w3.org/2000/svg"
                    fill="none"
                    viewBox="0 0 24 24"
                    className="inline-block h-4 w-4 stroke-current"
                >
                    <path
                        strokeLinecap="round"
                        strokeLinejoin="round"
                        strokeWidth="2"
                        d="M6 18L18 6M6 6l12 12"
                    ></path>
                </svg>
                not connected
            </div>
            <div className="w-full mt-40" />
            <div className="w-full h-full flex flex-col justify-center items-center gap-y-5">
                {children}
            </div>
            <div className="absolute bottom-0 left-0 w-[100%] h-[50%] bg-error rounded-t-[50%] blur-[130rem] opacity-10"></div>
        </div>
    )
}
