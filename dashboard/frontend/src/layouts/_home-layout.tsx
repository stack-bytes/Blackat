import { ReactNode } from "react";
import Navbar from "../components/Navbar";

export default function HomeLayout({children}:{children:ReactNode}){
    return(
        <div className="min-h-screen min-w-screen flex flex-col bg-base-100 p-5">
            <Navbar />
            <div className="w-full mt-40"/>
            <div className="w-full h-full flex flex-col justify-center items-center">
                {children}
            </div>

        </div>
    )
}