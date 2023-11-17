import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function MyRoutes() {
    const [routes, setRoutes] = useState([]);
    const navigate = useNavigate(); // v6

    useEffect(() => {
        fetchRoutes();
    }, []);

    const fetchRoutes = async () => {
        const userid = sessionStorage.getItem('LOGIN_USER_ID');
        try {
            const response = await axios.get(`http://localhost:8080/api/user/routes/${userid}`);
            console.log(response.data);
            setRoutes(Array.isArray(response.data) ? response.data : [response.data]);
        } catch (error) {
            console.error('경로를 불러오는 중 오류가 발생했습니다.', error);
        }
    };

    const handleViewRoute = (b_latitude, b_longitude, c_latitude, c_longitude) => {
        navigate(`/route-view?lat1=${b_latitude}&lng1=${b_longitude}&lat2=${c_latitude}&lng2=${c_longitude}`);
    };

    const handleDelete = async (routeSeq) => {
        console.log("Deleting route with sequence: ", routeSeq);
        const confirmDelete = window.confirm('경로를 삭제하시겠습니까?');

        if (confirmDelete) {
            try {
                await axios.delete(`http://localhost:8080/api/route-delete/${routeSeq}`);
                window.location.reload();
            } catch (error) {
                console.error('경로 삭제 중 오류가 발생했습니다.', error.response);
            }
        }
    };




    return (
        <div>
            <h2>내 경로</h2>
            {routes.length > 0 ? (
                <ul>
                    {routes.map((route, index) => (
                        <li key={index}>
                            {route.b_mark} - {route.c_mark}
                            <button onClick={() => handleViewRoute(route.b_latitude, route.b_longitude, route.c_latitude, route.c_longitude)}>
                                경로보기
                            </button>
                            <button onClick={() => handleDelete(route.routeSeq)}>
                                경로삭제
                            </button>
                        </li>
                    ))}
                </ul>
            ) : (
                <p>저장된 경로가 없습니다.</p>
            )}
        </div>
    );
}

export default MyRoutes;
