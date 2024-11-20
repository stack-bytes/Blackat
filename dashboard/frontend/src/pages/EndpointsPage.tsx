import { useEffect, useState } from "react";
import Endpoint, { EndpointInterface } from "../components/Endpoint";
import DefaultLayout from "../layouts/_default-layout"
import Client, { ClientInterface } from "../components/Client";

const EndpointsPage = () => {

    const [clientDetails, setClientDetails] = useState<ClientInterface[]>()

    const getClientDetails = async () => {
        const response  = await fetch("http://localhost:6782/dashboard/clients")

        const clientDetailsJson  : ClientInterface[] = await response.json();
        

        setClientDetails(clientDetailsJson);
    }

    useEffect(()=>{
        getClientDetails();
    },[])

    
    
    return(<>
        <DefaultLayout>
            <div className="w-full h-full flex flex-col items-center gap-y-5">
                {
                    clientDetails?.map((c, i) => (
                        <Client key={i} clientId={c.clientId} context={c.context} endpoints={c.endpoints}/>
                    ))
                }
            </div>

        </DefaultLayout>
    </>)
}

export default EndpointsPage;