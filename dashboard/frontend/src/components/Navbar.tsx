import { CatIcon } from "lucide-react";

const Navbar = () => {
    return (
        <>
            <div className="navbar bg-transparent">
                <div className="flex-1">
                    <a className="font-black text-4xl flex flex-row items-center justify-center gap-x-4"><CatIcon width={36} height={36}/>Blackat</a>
                </div>
                <div className="flex-none">
                    <ul className="menu menu-horizontal px-1">
                        <li>
                            <a>Link</a>
                        </li>
                        <li>
                            <details>
                                <summary>Parent</summary>
                                <ul className="bg-base-200 rounded-t-none p-2">
                                    <li>
                                        <a>Link 1</a>
                                    </li>
                                    <li>
                                        <a>Link 2</a>
                                    </li>
                                </ul>
                            </details>
                        </li>
                    </ul>
                </div>
            </div>
        </>
    )
}

export default Navbar;
