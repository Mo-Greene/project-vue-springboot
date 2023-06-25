import { createWebHistory, createRouter } from "vue-router";
import Free from '@/page/Free.vue';
import Notice from '@/page/Notice.vue';
import Attachment from "@/page/Attachment.vue";

const routes = [
    {
        path: "/free",
        name: "자유게시판",
        component: Free
    },
    {
        path: "/notice",
        name: "공지게시판",
        component: Notice
    },
    {
        path: "/attachment",
        name: "자료실",
        component: Attachment
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
