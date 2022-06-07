import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import style from '../../css/portfolio/portfolio_edit.module.css';
import PortfolioDescript from './PortfolioDescript';
import PortfolioEditAccess from './PortfolioEditAccess';
import PortfolioGit from './PortfolioGit';
import PortfolioImage from './PortfolioImage';
import PortfolioVideo from './PortfolioVideo';
import PortfolioYoutube from './PortfolioYoutube';

const PortfolioEdit = (props) => {
    const { id } = useParams();
    const { process } = props;
    const [currentOrder, setCurrentOrder] = useState(0);
    const [rows, setRows] = useState([]);
    const [isPublic, setIsPublic] = useState(true);
    const [portfolio, setPortfolio] = useState({});
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const fetchPortfolio = async () => {
            setLoading(true);
            try {
                await axios.get('/api/portfolio/' + id).then(response => {
                    setPortfolio(response.data.data.portfolio);
                    setIsPublic(response.data.data.portfolio.visibility);
                })
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        }
        if (process === "modify") {
            fetchPortfolio();
        }
    }, [])

    useEffect(() => {
        console.log(portfolio);
    }, [portfolio]);

    useEffect(() => {
        console.log(rows);
    }, [rows]);

    useEffect(() => {
        console.log(currentOrder);
    }, [currentOrder]);

    useEffect(() => {

    });

    const deleteRow = async (index) => {
        let nextRows = await rows.filter(row => row.index !== index);
        setRows(nextRows.map((row, index) => {
            return {
                ...row,
                index: index + 1
            };
        }))
        setCurrentOrder(currentOrder - 1);
    };

    const setDescript = (e, currentRow) => {
        setRows(rows.map(row => 
            row.index === currentRow.index ? {
                ...row,
                descript: e.target.value
            } : {
                ...row
            }
        ))
    }

    const setImage = (e, currentRow, imageUrl) => {
         setRows(rows.map(row => 
            row.index === currentRow.index ? {
                ...row,
                url: imageUrl
            } : {
                ...row
            }
        ))
    }

    const setVideo = (e, currentRow, videoUrl) => {
         setRows(rows.map(row => 
            row.index === currentRow.index ? {
                ...row,
                url: videoUrl
            } : {
                ...row
            }
        ))
    }

    const setYoutube = (e, currentRow, youtubeUrl) => {
         setRows(rows.map(row => 
            row.index === currentRow.index ? {
                ...row,
                url: youtubeUrl
            } : {
                ...row
            }
        ))
    }

    const setGit = (e, currentRow) => {
        setRows(rows.map(row => 
            row.index === currentRow.index ? {
                ...row,
                url: e.target.value
            } : {
                ...row
            }
        ))
    }


    if (loading) {
        return null;
    }

    return (
        <section className={style.main}>
            <div className="wrap">
                <div className={style.main_container}>
                    <div className={style.edit_area}>
                        <label className={style.portfolio_title}>
                            <input type="text" size="100" placeholder="제목을 입력하세요" />
                        </label>
                        <div className={style.portfolio_box}>
                            {
                                rows.map(row => {
                                    if (row.category === "descript") {
                                        return (<PortfolioDescript key={row.index} row={row} setDescript={setDescript} deleteRow={deleteRow}/>)
                                    } else if (row.category === "image") {
                                        return (<PortfolioImage key={row.index} row={row} setImage={setImage} deleteRow={deleteRow}/>)
                                    } else if (row.category === "video") {
                                        return (<PortfolioVideo key={row.index} row={row} setVideo={setVideo} deleteRow={deleteRow}/>)
                                    } else if (row.category === "youtube") {
                                        return (<PortfolioYoutube key={row.index} row={row} setYoutube={setYoutube} deleteRow={deleteRow}/>)
                                    } else if (row.category === "git") {
                                        return (<PortfolioGit key={row.index} row={row} setGit={setGit} deleteRow={deleteRow}/>)
                                    } else {
                                        return null
                                    }
                                })
                            }
                        </div>
                    </div>
                    <PortfolioEditAccess rows={rows} setRows={setRows} currentOrder={currentOrder} setCurrentOrder={setCurrentOrder} isPublic={isPublic} setIsPublic={setIsPublic} />
                </div>
            </div>
        </section>
    );
};

export default PortfolioEdit;