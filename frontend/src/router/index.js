import { createWebHistory, createRouter } from "vue-router";
import Login from "@/page/login/Login.vue";
import SignUp from "@/page/login/SignUp.vue";
const Free = () => import ('@/page/free/Free.vue');
const Notice = () => import ('@/page/notice/Notice.vue');
const Attachment = () => import ("@/page/attachment/Attachment.vue");
const FreeView = () => import ("@/page/free/FreeView.vue");
const NoticeView = () => import ("@/page/notice/NoticeView.vue");
const AttachmentView = () => import ("@/page/attachment/AttachmentView.vue");
const FreeWrite = () => import ("@/page/free/FreeWrite.vue");
const NoticeWrite = () => import ("@/page/notice/NoticeWrite.vue");
const AttachmentWrite = () => import ("@/page/attachment/AttachmentWrite.vue");
const FreeModify = () => import ("@/page/free/FreeModify.vue");
const NoticeModify = () => import ("@/page/notice/NoticeModify.vue");
const AttachmentModify = () => import ("@/page/attachment/AttachmentModify.vue");

const routes = [
    {
        path: "/login",
        name: "로그인",
        component: Login
    },
    {
        path: "/signup",
        name: "회원가입",
        component: SignUp
    },
    {
        path: "/free",
        name: "자유게시판",
        component: Free
    },
    {
        path: "/free/:boardNo",
        name: "자유게시글 상세조회",
        component: FreeView
    },
    {
        path: "/free/write",
        name: "자유게시글 등록",
        component: FreeWrite
    },
    {
        path: "/free/modify/:boardNo",
        name: "자유게시글 수정",
        component: FreeModify
    },
    {
        path: "/notice",
        name: "공지게시판",
        component: Notice
    },
    {
        path: "/notice/:boardNo",
        name: "공지게시판 상세조회",
        component: NoticeView
    },
    {
        path: "/notice/write",
        name: "공지게시글 등록",
        component: NoticeWrite
    },
    {
        path: "/notice/modify/:boardNo",
        name: "공지게시글 수정",
        component: NoticeModify
    },
    {
        path: "/attachment",
        name: "자료실",
        component: Attachment
    },
    {
        path: "/attachment/:boardNo",
        name: "자료실 상세조회",
        component: AttachmentView
    },
    {
        path: "/attachment/write",
        name: "자료실 등록",
        component: AttachmentWrite
    },
    {
        path: "/attachment/modify/:boardNo",
        name: "자료실 수정",
        component: AttachmentModify
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
