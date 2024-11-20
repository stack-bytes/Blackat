import { Route, Router, Routes } from "react-router-dom"
import HomePage from "./pages/HomePage"
import DashboardPage from "./pages/DashboardPage"
import OverviewPage from "./pages/OverviewPage"
import EndpointsPage from "./pages/EndpointsPage"

function App() {
    return (
        <>
            <Routes>
                <Route path="/" Component={HomePage} />
                <Route path="/dashboard" Component={DashboardPage} />
                <Route path="/overview" Component={OverviewPage} />
                <Route path="/endpoints" Component={EndpointsPage} />
            </Routes>
        </>
    )
}

export default App
