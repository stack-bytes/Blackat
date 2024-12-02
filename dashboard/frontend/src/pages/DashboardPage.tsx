import { useEffect, useState } from "react"
import DefaultLayout from "../layouts/_default-layout"
import DashboardClientContextComponent from "../components/DashboardClientContext"
import { AlertCircle, CatIcon } from "lucide-react"


export interface DashboardClientContext {
    clientId : string,
    serviceName: string,
    port: number,
    host: string,
    sameIp: boolean,
    url: string,
}

export interface RequestsNumberInterface{
    success: number,
    warning: number,
    error: number
}


const DashboardPage: React.FC = () => {
    
    const [dashboardClientContexts, setDashboardClientContexts] = useState<DashboardClientContext[]>([])






    const getContexts = async () => {
        const response  = await fetch("http://localhost:6782/dashboard/contexts")

        if(!response.ok){
            setDashboardClientContexts([]);
            return;
        }

        const clientContexts : DashboardClientContext[] = await response.json();
        
        

        setDashboardClientContexts(clientContexts)

        console.log(clientContexts)
    }

    useEffect(()=>{
        getContexts()
    },[])

    
    return (
        <DefaultLayout>
            <div className="w-full h-auto flex flex-col items-start gap-x-10 bg-neutral-900 rounded-lg p-3 mb-5">
                {
                    dashboardClientContexts.length > 0 ? 
                    dashboardClientContexts.map((c, k) => (
                        <DashboardClientContextComponent url={c.url} clientId={c.clientId} host={c.host} port={c.port} sameIp={c.sameIp} serviceName={c.serviceName} key={k}/>
                    ))
                    :
                    <div className="w-full h-64 flex flex-row items-center justify-center">
                        <h1 className="flex flex-row gap-x-3 text-5xl">No clients discovered! <CatIcon size={48}/></h1>
                    </div>
                }
            </div>
            <div className="w-full flex flex-row items-center justify-center h-auto gap-x-8 mb-5 ">
                    <div className="w-[33%] h-[30vh] rounded-lg bg-neutral-800 flex flex-col items-center justify-center gap-y-7">
                        <h1 className="text-8xl font-bold">
                            <span className="text-success drop-shadow-xl">0</span>
                            /
                            <span className="text-warning drop-shadow-xl">0</span>
                            /
                            <span className="text-error drop-shadow-xl">0</span>
                        </h1>
                        <p className="text-xl">Requests made last hour</p>
                    </div>
                    <div className="w-[33%] h-[30vh] rounded-lg  bg-neutral-800 flex flex-col items-center justify-center gap-y-7">
                        
                    </div>
                    <div className="w-[33%] h-[30vh] rounded-lg  bg-neutral-800 flex flex-col items-center justify-center gap-y-7">
                        
                    </div>
            </div>
            <div className="w-full h-auto flex flex-col items-start gap-x-10 bg-neutral-900 rounded-lg p-3">
                {
                    <div className="w-full h-64 flex flex-row items-center justify-center">
                        <h1 className="flex flex-row gap-x-3 text-5xl">No alerts found! <AlertCircle size={48}/></h1>
                    </div>
                }
            </div>
        </DefaultLayout>
    )
}

export default DashboardPage
