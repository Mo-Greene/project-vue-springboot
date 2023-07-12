<template>
    <v-card>
        <div class="d-flex align-center justify-center" style="height: 100vh">
            <v-sheet width="400" class="mx-auto">
                <v-form fast-fail ref="loginForm">
                    <v-card-title style="text-align: center">
                        SignUp
                    </v-card-title>
                    <v-card-item>
                        <v-text-field
                            variant="outlined"
                            v-model="username"
                            label="username"
                        ></v-text-field>

                        <v-text-field
                            variant="outlined"
                            v-model="password"
                            label="password"
                        ></v-text-field>

                        <v-btn
                            color="primary"
                            block
                            class="mt-2"
                            @click="submit">Submit</v-btn>
                    </v-card-item>
                </v-form>
            </v-sheet>
        </div>
    </v-card>
</template>

<script setup>

</script>
