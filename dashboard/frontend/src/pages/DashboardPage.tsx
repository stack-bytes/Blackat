import { useEffect, useState } from "react"
import DefaultLayout from "../layouts/_default-layout"
import DashboardClientContextComponent from "../components/DashboardClientContext"
import { CatIcon } from "lucide-react"


export interface DashboardClientContext {
    clientId : string,
    serviceName: string,
    port: number,
    host: string,
    sameIp: boolean,
    url: string,
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
            <div className="w-full h-auto flex flex-row items-start gap-x-10 bg-neutral-900 rounded-lg p-3">
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
        </DefaultLayout>
    )
}

export default DashboardPage
