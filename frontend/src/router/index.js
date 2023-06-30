import { createWebHistory, createRouter } from "vue-router";
import Free from '@/page/free/Free.vue';
import Notice from '@/page/notice/Notice.vue';
import Attachment from "@/page/attachment/Attachment.vue";
import FreeView from "@/page/free/FreeView.vue";
import NoticeView from "@/page/notice/NoticeView.vue";
import AttachmentView from "@/page/attachment/AttachmentView.vue";
import FreeWrite from "@/page/free/FreeWrite.vue";
import NoticeWrite from "@/page/notice/NoticeWrite.vue";
import AttachmentWrite from "@/page/attachment/AttachmentWrite.vue";
import FreeModify from "@/page/free/FreeModify.vue";
import NoticeModify from "@/page/notice/NoticeModify.vue";
import AttachmentModify from "@/page/attachment/AttachmentModify.vue";

const routes = [
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
