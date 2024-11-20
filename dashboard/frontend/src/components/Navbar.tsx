import {
    Binoculars,
    CatIcon,
    Github,
    LayoutDashboardIcon,
    SlashSquareIcon,
} from "lucide-react"
import { Link } from "react-router-dom"

const Navbar = () => {
    return (
        <>
            <div className="navbar bg-transparent">
                <div className="flex-1">
                    <Link
                        to={"/"}
                        className="font-black text-4xl flex flex-row items-center justify-center gap-x-4"
                    >
                        <CatIcon width={36} height={36} />
                        Blackat
                    </Link>
                </div>
                <div className="flex-none">
                    <ul className="flex flex-row justify-end items-center gap-x-3">
                        <li>
                            <Link
                                to={"/dashboard"}
                                className="btn btn-primary text-2xl font-semibold flex flex-row items-center gap-x-2"
                            >
                                <LayoutDashboardIcon />
                                <span className="hidden md:inline">
                                    Dashboard
                                </span>
                            </Link>
                        </li>
                        <li>
                            <Link to={"/endpoints"} className="btn btn-primary text-2xl font-semibold flex flex-row items-center gap-x-2">
                                <SlashSquareIcon />
                                <span className="hidden md:inline">
                                    Endpoints
                                </span>
                            </Link>
                        </li>
                        <li>
                            <Link
                                to="/overview"
                                className="btn btn-primary text-2xl font-semibold flex flex-row items-center gap-x-2"
                            >
                                <Binoculars />
                                <span className="hidden md:inline">
                                    Overview
                                </span>
                            </Link>
                        </li>
                        <div className="w-1 h-6 bg-primary rounded-full" />
                        <li>
                            <a className="btn  btn-primary text-2xl font-semibold flex flex-row items-center justify-center gap-x-2">
                                <Github />
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </>
    )
}

export default Navbar
