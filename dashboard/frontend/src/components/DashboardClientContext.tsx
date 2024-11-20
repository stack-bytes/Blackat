import { CatIcon, Cloud, Computer, Slash, SlashIcon, SlashSquareIcon } from "lucide-react";
import { DashboardClientContext } from "../pages/DashboardPage";


const DashboardClientContextComponent: React.FC<DashboardClientContext> = ({
    clientId,
    serviceName,
    port,
    host,
    sameIp,
    blackatUrl,
}) => {
    return(
        <div className="relative w-64 h-64 bg-neutral-800 rounded-lg flex flex-col items-center justify-center">
            {
                sameIp ? 
                <Computer className="absolute top-5 right-5" width={32} height={32}/>
                :
                <Cloud className="absolute top-5 right-5" width={32} height={32}/>
            }
            {
                <h1 className="absolute top-5 left-5 text-3xl font-bold text-accent"> {port}</h1>
            }
            <h1 className="w-[90%] flex flex-row items-center justify-center overflow-hidden text-2xl text-center font-medium">{serviceName}</h1>
            <button className="absolute bottom-2 left-2 w-14 h-14 rounded-md btn btn-primary flex flex-row items-center justify-center">
                <SlashSquareIcon/>
            </button>
            <a href={blackatUrl} className="absolute bottom-2 right-2 w-14 h-14 rounded-md btn btn-primary flex flex-row items-center justify-center">
                <CatIcon/>
            </a>
        </div>
    )
}

export default DashboardClientContextComponent;