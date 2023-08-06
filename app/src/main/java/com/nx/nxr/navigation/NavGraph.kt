package com.nx.nxr.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nx.nxr.core.Constants.ID
import com.nx.nxr.core.Constants.screenCreateRoute
import com.nx.nxr.core.Constants.screenDetailRoute
import com.nx.nxr.core.Constants.screenDetailRouteCall
import com.nx.nxr.core.Constants.screenEditRoute
import com.nx.nxr.core.Constants.screenEditRouteCall
import com.nx.nxr.core.Constants.screenListRoute
import com.nx.nxr.presentation.ScreenCreate
import com.nx.nxr.presentation.ScreenDetail
import com.nx.nxr.presentation.ScreenEdit
import com.nx.nxr.presentation.ScreenList

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = screenListRoute()
    ) {
        // screen list
        composable(screenListRoute()) {
            ScreenList(
                onCreateClicked = {
                    navController.navigate(screenCreateRoute())
                },
                onDetailClick = {
                    navController.navigate(screenDetailRouteCall(it))
                }
            )
        }
        // screen detail
        composable(
            screenDetailRoute(),
            arguments = listOf(
                navArgument(ID) { type = NavType.IntType }
            )
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getInt(ID)?.let {
                ScreenDetail(
                    nxrId = it,
                    onEditClicked = {
                        navController.navigate(screenEditRouteCall(it))
                    }
                )
            }
        }
        // screen edit
        composable(
            screenEditRoute(),
            arguments = listOf(
                navArgument(ID) { type = NavType.IntType }
            )
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getInt(ID)?.let {
                ScreenEdit(
                    nxrId = it,
                    onBackPressed = { navController.popBackStack()  }
                )
            }
        }
        // screen create
        composable(screenCreateRoute()) {
            ScreenCreate(
                onCreated = { navController.popBackStack() }
            )
        }
    }
}