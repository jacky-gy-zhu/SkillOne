const {resolve} = require('path')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const MiniCssExtractPlugin = require('mini-css-extract-plugin')
const OptimizeCssAssetsWebpackPlugin = require('optimize-css-assets-webpack-plugin')
const webpack = require('webpack')
const AddAssetHtmlWebpackPlugin = require('add-asset-html-webpack-plugin')
const PUBLIC_PATH = '/skillone/react/web/build/'

process.env.NODE_ENV = "production"

const commonCssLoader = [
    // usYloader取代style-loader，作用：提取js中的css成单独文件
    MiniCssExtractPlugin.loader,
    'css-loader',
    {
        loader: 'postcss-loader',
        options: {
            postcssOptions: {
                plugins: [
                    [
                        'postcss-preset-env',
                        {
                            // Options
                        },
                    ],
                ],
            },
        },
    }
]

module.exports = {
    entry: './src/index.js',
    output: {
        filename: 'js/[name].[contenthash:10].js',
        path: resolve(__dirname, 'build'),
        publicPath: PUBLIC_PATH
    },
    module: {
        rules: [
            {
                oneOf: [
                    {
                        test: /\.css$/,
                        use: [...commonCssLoader]
                    },
                    {
                        test: /\.less$/,
                        use: [
                            ...commonCssLoader,
                            'less-loader'
                        ]
                    },
                    {
                        test: /\.html$/,
                        loader: 'html-loader'
                    },
                    {
                        test: /\.(jpg|png|gif)$/,
                        loader: 'url-loader',
                        options: {
                            limit: 8 * 1024,
                            name: '[hash:10].[ext]',
                            outputPath: 'imgs'
                        }
                    },
                    {
                        exclude: /\.(css|js|jsx|html|less|png|jpg|jpeg|gif)$/,
                        loader: 'file-loader',
                        options: {
                            name: '[hash:10].[ext]',
                            outputPath: 'media'
                        }
                    },
                    {
                        test: /\.(js|jsx)$/,
                        exclude: /node_modules/,
                        use: [
                            {
                                loader: 'thread-loader',
                                options: {
                                    workers: 8
                                }
                            },
                            {
                                loader: 'babel-loader',
                                options: {
                                    presets: [
                                        "@babel/preset-env",
                                        "@babel/preset-react",
                                        {
                                            "plugins": ['@babel/plugin-proposal-class-properties']
                                        }
                                    ],
                                    cacheDirectory: true
                                }
                            }
                        ]
                    }
                ]
            }
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: './public/index.html',
            minify: {
                collapseWhitespace: true,
                removeComments: true
            }
        }),
        new MiniCssExtractPlugin({
            filename: 'css/main.[contenthash:10].css'
        }),
        new OptimizeCssAssetsWebpackPlugin(),
        new webpack.DllReferencePlugin({
            manifest: resolve(__dirname, 'dll/manifest.json')
        }),
        new AddAssetHtmlWebpackPlugin([
            {
                filepath: resolve(__dirname, 'dll/bootstrap.js'),
                outputPath: 'plugin',
                publicPath: PUBLIC_PATH + 'plugin'
            },
            {
                filepath: resolve(__dirname, 'dll/react.js'),
                outputPath: 'plugin',
                publicPath: PUBLIC_PATH + 'plugin'
            }
        ])
    ],
    mode: 'production',
    resolve: {
        extensions: ['.js', '.jsx']
    },
    devtool: 'cheap-module-source-map',
    optimization: {
        splitChunks: {
            chunks: 'all'
        }
    }
}