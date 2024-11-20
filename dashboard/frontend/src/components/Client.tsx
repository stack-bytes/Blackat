import { DashboardClientContext } from "../pages/DashboardPage";
import Endpoint, { EndpointInterface } from "./Endpoint";

export interface ClientInterface{
    clientId : string,
    context : DashboardClientContext,
    endpoints : EndpointInterface[]
} 

const Client : React.FC<ClientInterface> = ({clientId, context, endpoints}) => {
    return(
        <div className="w-full h-auto bg-neutral-900 rounded-xl p-5">
            <h1 className="text-3xl font-bold mb-5">{context.serviceName}</h1>
            <div className="flex flex-col items-center justify-center gap-y-5">
                {
                    endpoints.map((e,i)=>(
                        <Endpoint method={e.method} name={e.name} url={context.url+e.url} key={i}/>
                    ))
                }
            </div>
           
                
        </div>
    )
}


export default Client;