import daisyui from "daisyui"

export default {
    content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
    theme: {
        extend: {},
    },
    daisyui: {
        themes: [
            {
                blackat: {
                    primary: "#1e1e1e",
                    secondary: "#ff00ff",
                    accent: "#00ffff",
                    neutral: "#ff00ff",
                    "base-100": "#0d0d0d",
                    info: "#0000ff",
                    success: "#00ff00",
                    warning: "#edd834",
                    error: "#ff0000",
                },
            },
        ],
    },
    plugins: [daisyui],
}
