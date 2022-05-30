import React from 'react';
import HomeBanner from '../component/home/HomeBanner';
import HomeHeader from '../component/home/HomeHeader';
import HomeMain from '../component/home/HomeMain';

const HomePage = () => {
    return (
        <>
            <HomeHeader />
            <HomeBanner />
            <HomeMain />
        </>
    );
};

export default HomePage;