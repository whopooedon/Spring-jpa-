import React, { useState, useEffect, useRef } from 'react';
import axios from 'axios';
import mapboxgl from 'mapbox-gl';
import 'mapbox-gl/dist/mapbox-gl.css';

mapboxgl.accessToken = 'pk.eyJ1IjoiY2FwdGFpbjIwMDQ1IiwiYSI6ImNsb3BiajJ0dDA1MHMybnA2aml5ZXZrb3IifQ.YwY7ZH5DmThQJ1XJo7SudQ';

const MapWithMarkers = () => {
    const mapContainer = useRef(null);
    const [map, setMap] = useState(null);
    const [currentMarkers, setCurrentMarkers] = useState([]);
    const [districts, setDistricts] = useState([]);
    const [selectedDistrict, setSelectedDistrict] = useState('');
    const [neighborhoods, setNeighborhoods] = useState([]);
    const [selectedNeighborhood, setSelectedNeighborhood] = useState('');
    const [message, setMessage] = useState('');

    useEffect(() => {
        axios.get('http://localhost:8080/api/districts').then(response => {
            setDistricts(response.data);
        });
    }, []);

    useEffect(() => {
        if (selectedDistrict) {
            axios.get(`http://localhost:8080/api/districts/${selectedDistrict}/neighborhoods`).then(response => {
                setNeighborhoods(response.data);
            });
        }
    }, [selectedDistrict]);

    useEffect(() => {
        if (mapContainer.current && !map) {
            const newMap = new mapboxgl.Map({
                container: mapContainer.current,
                style: 'mapbox://styles/mapbox/streets-v11',
                center: [126.9780, 37.5665],
                zoom: 10
            });

            newMap.on('load', () => {
                setMap(newMap);
            });
        }
    }, [map]);

    const handleFind = () => {
        if (selectedDistrict && selectedNeighborhood) {
            setMessage('');
            axios.get(`http://localhost:8080/api/bike-locations?district=${selectedDistrict}&neighborhood=${selectedNeighborhood}`)
                .then(response => {
                    const locations = response.data;
                    if (locations.length === 0) {
                        alert('대여소가 없습니다.');
                    } else {
                        // 기존 마커 제거
                        currentMarkers.forEach(marker => marker.remove());
                        setCurrentMarkers([]); // 마커 배열 초기화

                        // 새로운 마커 추가
                        const newMarkers = locations.map(location => {
                            let clicked = false;
                            const popup = new mapboxgl.Popup({ offset: 25 }).setHTML(
                                `${location.bikeAddr1}</br>
                                <a href="/culture/${location.bikeAddr1}/${location.longitude}/${location.latitude}">문화정보 찾으러가기</a>`
                            );

                            const marker = new mapboxgl.Marker()
                                .setLngLat([location.longitude, location.latitude])
                                .setPopup(popup) // sets a popup on this marker
                                .addTo(map);

                            marker.getElement().addEventListener('mouseenter', () => {
                                popup.addTo(map);
                            });

                            // 마우스를 떼었을 때 클릭되지 않았다면 팝업 제거
                            marker.getElement().addEventListener('mouseleave', () => {
                                if (!clicked) {
                                    popup.remove();
                                }
                            });

                            // 클릭 시 팝업 상태 토글
                            marker.getElement().addEventListener('click', () => {
                                clicked = !clicked;
                                if (clicked) {
                                    popup.addTo(map);
                                } else {
                                    popup.remove();
                                }
                            });

                            return marker;
                        });

                        setCurrentMarkers(newMarkers); // 마커 배열 업데이트
                    }
                })
                .catch(error => {
                    console.error('Error fetching locations:', error);
                    setMessage('위치를 불러오는 데 실패했습니다.');
                });
        } else {
            setMessage('구와 동을 모두 선택해주세요.');
        }
    };

    return (
        <div>
            <select onChange={e => setSelectedDistrict(e.target.value)} value={selectedDistrict}>
                <option value="">구 선택</option>
                {districts.map(district => (
                    <option key={district} value={district}>{district}</option>
                ))}
            </select>

            <select onChange={e => setSelectedNeighborhood(e.target.value)} value={selectedNeighborhood}>
                <option value="">동 선택</option>
                {neighborhoods.map(neighborhood => (
                    <option key={neighborhood} value={neighborhood}>{neighborhood}</option>
                ))}
            </select>

            <button onClick={handleFind}>찾기</button>

            {message && <div>{message}</div>}

            <div ref={mapContainer} style={{ width: '100vw', height: '100vh' }} />
        </div>
    );
};

export default MapWithMarkers;