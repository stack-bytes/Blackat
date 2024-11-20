
export interface EndpointInterface {
    method: string,
    name : string, 
    url : string,
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
    url
}) => {
    return(
        <>
        <div tabIndex={0} className="collapse bg-neutral-800">
            <div className="collapse-title text-xl  font-medium flex flex-row items-center justify-between pl-5">
                <h1 className="text-pur">{name}</h1>
                <h1 className={`${methodColorsDictionary[method] || "text-neutral-600"}`}>{method || "*"}</h1>
            </div>
            <div className="collapse-content">
                <a className="text-xl font-semibold text-accent" href={url} target="_blank">{url}</a>
            </div>
        </div>
        </>
    )
}

export default Endpoint;