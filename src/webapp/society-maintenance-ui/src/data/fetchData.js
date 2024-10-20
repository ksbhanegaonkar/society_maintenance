import useAxiosPrivate from "../hooks/useAxiosPrivate";


export const FetchHello = async () => {
    const axiosPrivate = useAxiosPrivate();
    const response = await axiosPrivate.get("/hello");
    console.log("axios response :::"+response);
}