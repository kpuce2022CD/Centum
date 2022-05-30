import React from 'react';
import Header from '../component/common/Header';
import SignUpAuth from '../component/member/SignUpAuth';
import SignUpForm from '../component/member/SignUpForm';
import SignUpProgress from '../component/member/SignUpProgress';
import SignUpResult from '../component/member/SignUpResult';
import SignUpTerms from '../component/member/SignUpTerms';
import TokenExpired from '../component/member/TokenExpired';

const SignUpPage = (props) => {
    const { currentPage } = props;
    return (
        <>
            <Header />
            <SignUpProgress currentPage={currentPage} />
            {
                {
                    "terms": <SignUpTerms />,
                    "form": <SignUpForm />,
                    "auth": <SignUpAuth />,
                    "result": <SignUpResult />,
                    "expire": <TokenExpired />
                }[currentPage]
            }
        </>
    );
};

export default SignUpPage;