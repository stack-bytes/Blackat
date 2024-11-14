import { CatIcon } from "lucide-react";
import HomeLayout from "../layouts/_home-layout";

const HomePage = () => {
    return(<>
        <HomeLayout>
            <h1 className="font-black text-8xl flex flex-row items-center justify-center gap-x-4"><CatIcon width={86} height={86}/> Blackat</h1>
            <p className="mt-6 text-2xl">Up and running #APINAME# </p>
        </HomeLayout>
    </>)
}

export default HomePage;