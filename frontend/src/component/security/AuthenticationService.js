import axios from 'axios';

class AuthenticationService{

    executeJwtAuthentication(loginId, passwd) {
        return axios.post("/api/login", {
            loginId: loginId,
            passwd: passwd
        })
    }

    registerSuccessfulLoginForJwt(loginId, token) {
        localStorage.setItem("token", token);
        localStorage.setItem("authenticatedUser", loginId);
    }

    createJwtToken(token) {
        return "Bearer " + token;
    }

    logout() {
        localStorage.removeItem("authenticatedUser");
        localStorage.removeItem("token");
    }

    isUserLoggedIn() {
        const token = localStorage.getItem("token");

        if (token) {
            return true;
        }
        return false;
    }

    getLoggedInLoginId() {
        const user = localStorage.getItem("authenticatedUser");
        
        if (user === null) {
            return '';
        }
        return user;
    }

}

export default new AuthenticationService();