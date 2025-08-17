import useAxiosPrivate from "../hooks/useAxiosPrivate";


export const FetchHello = async () => {
    const axiosPrivate = useAxiosPrivate();
    const response = await axiosPrivate.get("/hello");
}

export const SaveMaintenanceDetail = async (period, monthSelected, amount) => {
    const axiosPrivate = useAxiosPrivate();
    await axiosPrivate.post("/saveMaintenance",  JSON.stringify({period, monthSelected, amount}) );
}