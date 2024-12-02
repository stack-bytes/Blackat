import { LucideCircleDotDashed, MoreHorizontal, ScrollText } from "lucide-react";
import { LegacyRef, MutableRefObject, RefObject, useRef, useEffect, useState, ChangeEvent } from "react"
import HistoryElement, { HistoryElementArgsInterface } from "./HistoryElement";
import ColoredMethod from "./ColoredMethod";
import RequestBody from "./RequestBody";

export interface EndpointInterface {
    method: string,
    name : string, 
    url : string,
    parameters: ParamInterface[]
}

export interface ParamInterface{
    name: String,
    type: String,
}



const Endpoint : React.FC<EndpointInterface> = ({
    method,
    name,
    url,
    parameters
}) => {

    const inputRef = useRef (null);

    const [parametersValues, setParametersValues] = useState<any>(null);
    const [collapsed, setCollapsed] = useState<boolean>(true);
    const [recentHistory, setRecentHistory] = useState<HistoryElementArgsInterface | null>(null)
    const [requestBody, setRequestBody] = useState<String>("");


    const sendRequest = async ()  => {
        let tempHistory = recentHistory;
            tempHistory = {
                url: "",
                status: "",
                response: "",
                timing: -1,
                method: method
            }
       let idx = 0;
       for(const key in parametersValues){
        if(idx == 0)
            url+=`?`
        else
            url+=`&`
        url+=`${key}=${parametersValues[key]}`
        idx+=1;
       }
       tempHistory.url=url;

        const startTime = Date.now();

        const result = await fetch(url, {
            method: method,
            headers: {
                "Content-Type": "application/json" //TODO: Changeable
            },
            body: JSON.stringify(requestBody)    
        })


        const endTime = Date.now();

        tempHistory.status = result.status.toString();   
        tempHistory.timing = endTime - startTime;

        let resultContentType : String  | null = result.headers.get("content-type")
        let mess;

        if(resultContentType?.includes("application/json")){
           mess = await result.json();
        } else {
            mess = await result.text();
        }

        tempHistory.response = mess;

        setRecentHistory(tempHistory);

        

        console.log(mess)
        return mess;
    }

    //Define types for consistency
    useEffect(()=>{
        let tem : any = {}
        parameters.forEach((p)=>tem[`${p.name}`]=null)
        setParametersValues(tem)
    },[])

    const modifyParam = (paramKey: String, paramValue: string) => {
        let tem = parametersValues;
        tem[`${paramKey}`]=paramValue;
        console.log(tem)
        setParametersValues(tem)
    }
    

    return(
        <>
        <div className=" bg-neutral-800 collapse-arrow w-full h-auto rounded-lg p-4 flex flex-col gap-y-3">
            <div className="text-xl  font-medium flex flex-row items-center justify-between">
                <h1 className="text-pur">{name}</h1>
                <div className="flex flex-row w-auto h-auto gap-x-5">
                    <ColoredMethod method={method}/>
                    <button onClick={()=>setCollapsed(!collapsed)}><MoreHorizontal/></button>
                </div>

            </div>
            <div className={`${collapsed ? "hidden":"show"} flex flex-col  gap-y-3`}>
                <a className="text-xl font-semibold text-accent" href={url} target="_blank">{url}</a>
                {
                    parameters.length > 0 ?
                    <div className="w-auto h-auto p-4 bg-neutral-900 gap-y-4 flex flex-col mt-4 rounded-md mb-3">
                        {
                              parameters.map((p)=>(
                                <div className="flex flex-row gap-x-4">
                                    <p>?{p.name} : <span className="text-warning">{p.type} =</span></p>
                                    <input className="bg-neutral-800" ref={inputRef} onChange={(e)=>{
                                        modifyParam(p.name, e.target.value)
                                    }}></input>
                                </div>
                            ))
                        }
                    </div>
                    :<></>
                }
               <textarea className="w-full h-auto min-h-20 rounded-md bg-neutral-900 p-2"  placeholder="Enter request body here!" onChange={(e)=>{setRequestBody(e.target.value)}}>

                </textarea>
            <div className="flex flex-col w-full h-auto p-1 justify-center items-center">
                <h1 className="text-2xl flex flex-row gap-x-3 items-center justify-center">History  <ScrollText/></h1>
                <div className="divider"/>
                {
                    recentHistory ?
                    <HistoryElement method={recentHistory.method} response={recentHistory.response} status={recentHistory.status} timing={recentHistory.timing} url={recentHistory.url}/>
                    :
                    <h1 className="flex flex-row gap-x-3 font-medium">No last request to be shown</h1>
                }

            </div>

            
               
                <button className="mt-5 btn btn-primary rounded-lg text-xl font-bold transition-none no-animation"
            onClick={()=>sendRequest()}
            >Send</button>
            </div>
            
        </div>
        </>
    )
}

export default Endpoint;