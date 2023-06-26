import { createWebHistory, createRouter } from "vue-router";
import Free from '@/page/free/Free.vue';
import Notice from '@/page/notice/Notice.vue';
import Attachment from "@/page/attachment/Attachment.vue";
import FreeView from "@/page/free/FreeView.vue";
import NoticeView from "@/page/notice/NoticeView.vue";
import AttachmentView from "@/page/attachment/AttachmentView.vue";

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
        path: "/attachment",
        name: "자료실",
        component: Attachment
    },
    {
        path: "/attachment/:boardNo",
        name: "자료실 상세조회",
        component: AttachmentView
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
