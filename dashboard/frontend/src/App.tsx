import { Route, Router, Routes } from "react-router-dom"
import HomePage from "./pages/HomePage"
import DashboardPage from "./pages/DashboardPage"
import OverviewPage from "./pages/OverviewPage"

function App() {
    return (
        <>
            <Routes>
                <Route path="/" Component={HomePage} />
                <Route path="/dashboard" Component={DashboardPage} />
                <Route path="/overview" Component={OverviewPage} />
            </Routes>
        </>
    )
}

export default App
