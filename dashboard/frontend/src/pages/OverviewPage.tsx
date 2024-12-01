import MDEditor from "@uiw/react-md-editor"
import DefaultLayout from "../layouts/_default-layout"
import { useState } from "react"
import { Paperclip } from "lucide-react";

const OverviewPage = () => {

    return (
        <>
            <DefaultLayout>
                <div className="w-auto flex flex-row gap-x-5">
                <input
                    type="text"
                    placeholder="API Name"
                    className="input input-bordered w-full max-w-xs mb-3"
                />
                <input 
                    type="text"
                    placeholder="Version"
                    className="input input-bordered w-28 max-w-xs mb-3"/>
                </div>

                <textarea
                className="input input-bordered p-2 min-h-80 resize-none"
                placeholder="Start describing wat your api does here!"
                />
                
                <div className="mt-3 flex flex-row w-auto">
                    <button className="btn btn-outline">Export as PDF <Paperclip width={16}/></button>
                </div>
            </DefaultLayout>
        </>
    )
}

export default OverviewPage
