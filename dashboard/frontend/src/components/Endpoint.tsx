import { LegacyRef, MutableRefObject, RefObject, useRef } from "react"

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

const methodColorsDictionary : {[key:string]:string} = {
    "GET":"text-success",
    "POST":"text-error",
    "PUT":"text-yellow-500",
    "PATCH":"text-purple-500",
    "OPTIONS":"text-indigo-500"
}

const Endpoint : React.FC<EndpointInterface> = ({
    method,
    name,
    url,
    parameters
}) => {

    const inputRef = useRef (null);

    return(
        <>
        <div tabIndex={0} className="collapse bg-neutral-800 collapse-arrow">
            <div className="collapse-title text-xl  font-medium flex flex-row items-center justify-between pl-5">
                <h1 className="text-pur">{name}</h1>
                <h1 className={`${methodColorsDictionary[method] || "text-neutral-600"}`}>{method || "*"}</h1>
            </div>
            <div className="collapse-content">
                <a className="text-xl font-semibold text-accent" href={url} target="_blank">{url}</a>
                {
                    parameters.length > 0 ?
                    <div className="w-auto h-auto p-4 bg-neutral-900 gap-y-4 flex flex-col mt-4 rounded-md">
                        {
                              parameters.map((p)=>(
                                <div className="flex flex-row gap-x-4">
                                    <p>?{p.name} : <span className="text-warning">{p.type} =</span></p>
                                    <input className="bg-neutral-800" ref={inputRef}></input>
                                </div>
                            ))
                        }
                    </div>
                    :<></>
                }
            </div>
            <button className="btn btn-primary rounded-tl-none rounded-tr-none text-xl font-bold transition-none no-animation">Send</button>
        </div>
        </>
    )
}

export default Endpoint;