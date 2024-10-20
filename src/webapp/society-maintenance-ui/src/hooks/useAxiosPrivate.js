import { axiosPrivate } from "../api/axios";
import useAuth from "./useAuth";

const useAxiosPrivate = () => {
    const { auth } = useAuth();
    axiosPrivate.interceptors.request.use(
            config => {
                if (!config.headers['Authorization']) {
                    config.headers['Authorization'] = `Bearer ${auth?.accessToken}`;
                }
                return config;
            }, (error) => Promise.reject(error)
        );
    return axiosPrivate;
}

export default useAxiosPrivate;