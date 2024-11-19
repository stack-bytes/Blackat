import { ReactNode, useEffect, useState } from "react"
import Navbar from "../components/Navbar"
import { CheckCheck, CrossIcon, X } from "lucide-react";

export default function HomeLayout({ children }: { children: ReactNode }) {

    const [activeConnection, setActiveConnection ] = useState<Boolean>(false);
    
    const isConnectionActive = async () => {
        const response  = await fetch("http://localhost:6782/dashboard/sanity")

        if(!response.ok){
            setActiveConnection(false);
            return;
        }

        const status : boolean = await response.json();
        

        setActiveConnection(status);
    }

    useEffect(()=>{
        isConnectionActive();
    },[])

    return (
        <div className="min-h-screen min-w-screen flex flex-col bg-base-100 p-5">
            <Navbar />
            <div className={`badge ${ !activeConnection ? "badge-error" : "badge-success"}  badge-outline gap-2 h-8`}>
                {!activeConnection ? 
                <><X/></> : <><CheckCheck/></>
                }
                { !activeConnection ? 
                "Connection failed!" : 
                "Connected"}
            </div>
            <div className="w-full mt-40" />
            <div className="w-full h-full flex flex-col justify-center items-center gap-y-5">
                {children}
            </div>
            <div className={`absolute bottom-0 left-0 w-[100%] h-[50%] ${!activeConnection ?  "bg-error" : "bg-success"} rounded-t-[50%] blur-[130rem] opacity-10`}></div>
        </div>
    )
}
