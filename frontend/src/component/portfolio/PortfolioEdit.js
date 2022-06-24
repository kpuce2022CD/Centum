import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import style from '../../css/portfolio/portfolio_edit.module.css';
import instance from '../security/Interceptor';
import PortfolioDescriptEdit from './PortfolioDescriptEdit';
import PortfolioEditAccess from './PortfolioEditAccess';
import PortfolioGitEdit from './PortfolioGitEdit';
import PortfolioImageEdit from './PortfolioImageEdit';
import PortfolioVideoEdit from './PortfolioVideoEdit';
import PortfolioYoutubeEdit from './PortfolioYoutubeEdit';

const PortfolioEdit = (props) => {
    const { id } = useParams();
    const { process } = props;
    const [currentOrder, setCurrentOrder] = useState(0);
    const [rows, setRows] = useState([]);
    const [portfolio, setPortfolio] = useState({
        title: "",
        visibility: true
    });
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchPortfolio = async () => {
            setLoading(true);
            try {
                await instance.get('/api/portfolios/' + id).then(response => {
                    setPortfolio(response.data.data.portfolio);
                    setRows(response.data.data.portfolio.portfolioRows);
                    setCurrentOrder(response.data.data.portfolio.portfolioRows[response.data.data.portfolio.portfolioRows.length - 1].rowOrder);
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
        const nextPortfolio = {
            ...portfolio,
            portfolioRows: rows
        };
        setPortfolio(nextPortfolio);
    }, [rows]);

    useEffect(() => {
        console.log('currentOrder: ' + currentOrder);
    }, [currentOrder]);

    const postPortfolio = async () => {
        setLoading(true);
        try {
            await instance.post('/api/portfolios', portfolio).then(response => {
                console.log(response.data.data.portfolio);
                navigate('/portfolios/' + response.data.data.portfolio.id);
            });
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    }

    const deleteRow = async (rowOrder) => {
        let nextRows = await rows.filter(row => row.rowOrder !== rowOrder);
        setRows(nextRows.map((row, index) => {
            return {
                ...row,
                rowOrder: index + 1
            };
        }))
        setCurrentOrder(currentOrder - 1);
    };

    const setTitle = (e) => {
        const nextPortfolio = {
            ...portfolio,
            title: e.target.value
        };
        setPortfolio(nextPortfolio);
    }

    const setDescript = (e, currentRow) => {
        setRows(rows.map(row => 
            row.rowOrder === currentRow.rowOrder ? {
                ...row,
                saveType: "text",
                contents: e.target.value
            } : {
                ...row
            }
        ))
    }

    const setImage = (e, currentRow, imageUrl) => {
         setRows(rows.map(row => 
            row.rowOrder === currentRow.rowOrder ? {
                ...row,
                saveType: "url",
                contents: imageUrl
            } : {
                ...row
            }
        ))
    }

    const setVideo = (e, currentRow, videoUrl) => {
         setRows(rows.map(row => 
            row.rowOrder === currentRow.rowOrder ? {
                ...row,
                saveType: "url",
                contents: videoUrl
            } : {
                ...row
            }
        ))
    }

    const setYoutube = (e, currentRow, youtubeUrl) => {
         setRows(rows.map(row => 
            row.rowOrder === currentRow.rowOrder ? {
                ...row,
                saveType: "url",
                contents: youtubeUrl
            } : {
                ...row
            }
        ))
    }

    const setGit = (e, currentRow) => {
        setRows(rows.map(row => 
            row.rowOrder === currentRow.rowOrder ? {
                ...row,
                saveType: "url",
                contents: e.target.value
            } : {
                ...row
            }
        ))
    }

    const setVisibility = (isPublic) => {
        const nextPortfolio = {
            ...portfolio,
            visibility: isPublic
        };
        setPortfolio(nextPortfolio);
    };


    if (loading) {
        return null;
    }

    if (process === "modify" && !portfolio) {
        return null;
    }

    return (
        <section className={style.main}>
            <div className="wrap">
                <div className={style.main_container}>
                    <div className={style.edit_area}>
                        <label className={style.portfolio_title}>
                            <input type="text" size="100" placeholder="제목을 입력하세요" onChange={setTitle} defaultValue={portfolio.title} />
                        </label>
                        <div className={style.portfolio_box}>
                            {
                                rows.map(row => {
                                    if (row.rowType === "descript") {
                                        return (<PortfolioDescriptEdit key={row.rowOrder} row={row} setDescript={setDescript} deleteRow={deleteRow} process={process} />)
                                    } else if (row.rowType === "image") {
                                        return (<PortfolioImageEdit key={row.rowOrder} row={row} setImage={setImage} deleteRow={deleteRow} process={process} />)
                                    } else if (row.rowType === "video") {
                                        return (<PortfolioVideoEdit key={row.rowOrder} row={row} setVideo={setVideo} deleteRow={deleteRow} process={process} />)
                                    } else if (row.rowType === "youtube") {
                                        return (<PortfolioYoutubeEdit key={row.rowOrder} row={row} setYoutube={setYoutube} deleteRow={deleteRow} process={process} />)
                                    } else if (row.rowType === "github") {
                                        return (<PortfolioGitEdit key={row.rowOrder} row={row} setGit={setGit} deleteRow={deleteRow} process={process} />)
                                    } else {
                                        return null
                                    }
                                })
                            }
                        </div>
                    </div>
                    <PortfolioEditAccess rows={rows} setRows={setRows} currentOrder={currentOrder} setCurrentOrder={setCurrentOrder} visibility={portfolio.visibility} setVisibility={setVisibility} postPortfolio={postPortfolio} />
                </div>
            </div>
        </section>
    );
};

export default PortfolioEdit;