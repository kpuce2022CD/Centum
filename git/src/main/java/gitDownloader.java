public class gitDownloader {
    public static void main(String[] args) {
        GitApiImpl gitApi = new GitApiImpl();

        // personal token 입력
        gitApi.gitAccess("");

        // 저장 경로 설정
        gitApi.getAllPublicRepo();
    }
}
