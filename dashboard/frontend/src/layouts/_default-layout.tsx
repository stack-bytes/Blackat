import { ReactNode } from "react"
import Navbar from "../components/Navbar"

export default function DefaultLayout({ children }: { children: ReactNode }) {
    return (
        <div className="min-h-screen min-w-screen flex flex-col bg-base-100 p-5">
            <Navbar />
            <div className="flex-grow bg-primary rounded-2xl p-5">
                {children}
            </div>
        </div>
    )
}
