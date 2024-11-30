import ColoredMethod from "./ColoredMethod";


export interface HistoryElementArgsInterface{
    url: string,
    timing: number,
    status: string,
    response: string,
    method: string,
}

const getStatusColor = (status: string) => {
    let statusNumber = Number(status); 
    if(statusNumber >= 200 && statusNumber < 300)
        return "badge-success";
    if(statusNumber >= 300 && statusNumber < 400)
        return "badge-warning";
    if(statusNumber >= 400)
        return "badge-error";
    return "badge-error"
}

const HistoryElement : React.FC<HistoryElementArgsInterface> = ({
    url,
    timing,
    status,
    response,
    method
}) => {
    return(<>
        <div className="w-full h-auto p-5 bg-neutral-900 rounded-lg flex flex-col items-center">
            <div className=" flex flex-row justify-between w-full h-auto">
                <h1 className="text-xl font-medium flex flex-row gap-x-3">Response for 
                    <span className="font-bold"><ColoredMethod method={method}/> </span>
                    @ 
                    {url}
                </h1>
                <div className="flex flex-row gap-x-4">
                    <div className="badge badge-primary p-2">{timing*1000} ms</div>
                    <div className={`badge ${getStatusColor(status)}`}>{status}</div>
                </div>
                   
  
                
            </div>
            <div className="w-full h-auto p-3 bg-neutral-800 rounded-lg mt-5">
                {
                    typeof response === "string"?
                        response
                        :
                        JSON.stringify(response)
                }
            </div>
              
        </div>
    </>)
}

export default HistoryElement;