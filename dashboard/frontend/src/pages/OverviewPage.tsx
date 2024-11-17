import DefaultLayout from "../layouts/_default-layout"

const OverviewPage = () => {
    return (
        <>
            <DefaultLayout>
                <input
                    type="text"
                    placeholder="API Name"
                    className="input input-bordered w-full max-w-xs mb-3"
                />
                <textarea
                    placeholder="Write API description here"
                    className="input input-bordered w-full h-72"
                />
                <div className="mt-3 flex flex-row w-auto">
                    <button className="btn btn-outline">Export as PDF</button>
                </div>
            </DefaultLayout>
        </>
    )
}

export default OverviewPage
