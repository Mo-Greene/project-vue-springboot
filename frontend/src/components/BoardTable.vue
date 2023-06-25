<template>
    <v-container>
        <v-table
                fixed-header
                height="auto"
        >
            <thead>
            <tr>
                <th class="text-left">
                    No
                </th>
                <th class="text-left">
                    Title
                </th>
                <th class="text-left">
                    Writer
                </th>
                <th class="text-left">
                    View
                </th>
                <th class="text-left">
                    RegDate
                </th>
                <th class="text-left">
                    ModDate
                </th>
            </tr>
            </thead>
            <tbody>
            <tr
                    v-for="item in desserts"
                    :key="item.name"
            >
                <td>{{ item.name }}</td>
                <td>{{ item.calories }}</td>
            </tr>
            </tbody>
        </v-table>
    </v-container>
</template>

<script>
export default {
    name: "BoardTable",
    data () {
        return {
            desserts: [
                {
                    name: 'Frozen Yogurt',
                    calories: 159,
                },
                {
                    name: 'Ice cream sandwich',
                    calories: 237,
                },
                {
                    name: 'Eclair',
                    calories: 262,
                },
                {
                    name: 'Cupcake',
                    calories: 305,
                },
                {
                    name: 'Gingerbread',
                    calories: 356,
                },
                {
                    name: 'Jelly bean',
                    calories: 375,
                },
                {
                    name: 'Lollipop',
                    calories: 392,
                },
                {
                    name: 'Honeycomb',
                    calories: 408,
                },
                {
                    name: 'Donut',
                    calories: 452,
                },
                {
                    name: 'KitKat',
                    calories: 518,
                },
            ],
        }
    },
}
</script>
