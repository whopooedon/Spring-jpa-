import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';

const CulturePage = () => {
    const { bike_addr1 } = useParams();
    const { latitude } = useParams();
    const { longitude } = useParams();
    const [cultureData, setCultureData] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    let address = bike_addr1;
    let parts = address.split(" ");
    let district = parts.length >= 2 ? parts[1] : address;

    useEffect(() => {
        const fetchCultureData = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/culture-locations?district=${district}`);
                setCultureData(response.data);
                setIsLoading(false);
            } catch (error) {
                setError(error);
                setIsLoading(false);
            }
        };

        fetchCultureData();
    }, [district]);

    if (isLoading) return <div>Loading...</div>;
    if (error) return <div>Error: {error.message}</div>;

    return (
        <div>
            <h2>{district} 문화 정보</h2>
            <ul>
                {cultureData.map((item, index) => (
                    <li key={index}>
                        <Link to={`/path?lat1=${latitude}&lng1=${longitude}&lat2=${item.c_latitude}&lng2=${item.c_longitude}&b_mark=${bike_addr1}&c_mark=${item.culture_name}`}>
                            <h3>{item.culture_name}</h3>
                        </Link>
                        <p>위도: {item.c_latitude}</p>
                        <p>경도: {item.c_longitude}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default CulturePage;
