import MDEditor from "@uiw/react-md-editor"
import DefaultLayout from "../layouts/_default-layout"
import { useState } from "react"

const OverviewPage = () => {

    const [docs,setDocs] = useState<string>("");
    return (
        <>
            <DefaultLayout>
                <input
                    type="text"
                    placeholder="API Name"
                    className="input input-bordered w-full max-w-xs mb-3"
                />
                    <div className="container">
                        <MDEditor
                        value={docs}
                        onChange={setDocs}
                        preview={"edit"}
                        className="bg-black"
                    />
                    <MDEditor.Markdown source={docs} style={{ whiteSpace: 'pre-wrap' }} />
                </div>
                <div className="mt-3 flex flex-row w-auto">
                    <button className="btn btn-outline">Export as PDF</button>
                </div>
            </DefaultLayout>
        </>
    )
}

export default OverviewPage
