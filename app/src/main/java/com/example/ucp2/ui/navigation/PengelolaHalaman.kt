package com.example.ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2.ui.view.dokter.DestinasiInsertDr
import com.example.ucp2.ui.view.dokter.HomeDrView
import com.example.ucp2.ui.view.dokter.InsertDrView
import com.example.ucp2.ui.view.jadwal.DestinasiInsertJad
import com.example.ucp2.ui.view.jadwal.DetailJadView
import com.example.ucp2.ui.view.jadwal.HomeJadView
import com.example.ucp2.ui.view.jadwal.InsertJadView
import com.example.ucp2.ui.view.jadwal.UpdateJadView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = RouteNavigation.DestinasiHomeDr.route) {
        composable(route = RouteNavigation.DestinasiHomeDr.route) {
            HomeDrView(
                onAddDr = {
                    navController.navigate(DestinasiInsertDr.route)
                },
                onAddJad = {
                    navController.navigate(RouteNavigation.DestinasiHomeJad.route) // Mengarahkan ke halaman Jadwal
                },
                modifier = modifier
            )
        }
        composable(route = DestinasiInsertDr.route) {
            InsertDrView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier
            )
        }
        composable(route = RouteNavigation.DestinasiHomeJad.route) {
            HomeJadView(
                onBack = { navController.popBackStack() },
                onDetailClick = { id ->
                    navController.navigate("${RouteNavigation.DestinasiDetailJad.route}/$id")
                },
                onAddJad = {
                    navController.navigate(DestinasiInsertJad.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiInsertJad.route
        ) {
            InsertJadView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }

        composable(
            RouteNavigation.DestinasiDetailJad.routeWithArgs,
            arguments = listOf(
                navArgument(RouteNavigation.DestinasiDetailJad.id) {
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString(RouteNavigation.DestinasiDetailJad.id)
            id?.let { id ->
                DetailJadView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${RouteNavigation.DestinasiEditJad.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            RouteNavigation.DestinasiEditJad.routeWithArgs,
            arguments = listOf(
                navArgument(RouteNavigation.DestinasiEditJad.id) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateJadView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }
    }
}