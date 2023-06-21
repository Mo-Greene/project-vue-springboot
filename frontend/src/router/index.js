import { createWebHistory, createRouter } from "vue-router";

const routes = [
    {
        // path: "/",
        // name: "HelloWorld",
        // component: HelloWorld,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
