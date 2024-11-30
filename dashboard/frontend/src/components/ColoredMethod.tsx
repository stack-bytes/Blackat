export const methodColorsDictionary : {[key:string]:string} = {
    "GET":"text-success",
    "POST":"text-error",
    "PUT":"text-yellow-500",
    "PATCH":"text-purple-500",
    "OPTIONS":"text-indigo-500"
}
interface ColoredMethodProps {
    method: string;
}

const ColoredMethod : React.FC<ColoredMethodProps> = ({method}) =>  {
    return(
        <h1 className={`${methodColorsDictionary[method] || "text-neutral-600"}`}>{method || "*"}</h1>
    )
}

export default ColoredMethod;