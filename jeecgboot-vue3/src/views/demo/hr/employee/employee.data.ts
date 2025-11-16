import { BasicColumn, FormSchema } from '/@/components/Table';
import type { FormSchema as FormSchemaType } from '/@/components/Table';
import { h } from 'vue';

// 字典映射
const genderMap = {
  male: '男',
  female: '女',
};

const statusMap = {
  active: '在职',
  inactive: '离职',
  trial: '试用',
  retired: '退休',
};

const educationMap = {
  doctor: '博士',
  master: '硕士',
  bachelor: '本科',
  college: '专科',
  high_school: '高中',
  other: '其他',
};

// 表格列配置
export const columns: BasicColumn[] = [
  {
    title: '员工工号',
    dataIndex: 'employeeNo',
    width: 120,
    sorter: true,
  },
  {
    title: '姓名',
    dataIndex: 'name',
    width: 100,
    sorter: true,
  },
  {
    title: '性别',
    dataIndex: 'gender',
    width: 80,
    align: 'center',
    customRender: ({ record }) => genderMap[record.gender] || record.gender,
  },
  {
    title: '出生日期',
    dataIndex: 'birthDate',
    width: 100,
    sorter: true,
  },
  {
    title: '身份证号',
    dataIndex: 'idCard',
    width: 160,
  },
  {
    title: '手机号码',
    dataIndex: 'mobile',
    width: 120,
  },
  {
    title: '邮箱',
    dataIndex: 'email',
    width: 180,
  },
  {
    title: '职位',
    dataIndex: 'position',
    width: 120,
    sorter: true,
  },
  {
    title: '员工状态',
    dataIndex: 'employeeStatus',
    width: 100,
    align: 'center',
    customRender: ({ record }) => {
      const statusColors: Record<string, string> = {
        active: 'green',
        inactive: 'red',
        trial: 'orange',
        retired: 'gray',
      };
      const color = statusColors[record.employeeStatus] || 'default';
      const statusText = statusMap[record.employeeStatus] || record.employeeStatus;
      return h(
        'a-tag',
        { color },
        {
          default: () => statusText,
        }
      );
    },
  },
  {
    title: '学历',
    dataIndex: 'education',
    width: 100,
    align: 'center',
    customRender: ({ record }) => educationMap[record.education] || record.education,
  },
  {
    title: '入职日期',
    dataIndex: 'hireDate',
    width: 100,
    sorter: true,
  },
  {
    title: '紧急联系人',
    dataIndex: 'emergencyContact',
    width: 100,
  },
  {
    title: '紧急联系电话',
    dataIndex: 'emergencyPhone',
    width: 120,
  },
  {
    title: '备注',
    dataIndex: 'remark',
    width: 150,
    ellipsis: true,
  },
];

// 搜索表单配置
export const searchFormSchema: FormSchema[] = [
  {
    field: 'employeeNo',
    label: '员工工号',
    component: 'Input',
    colProps: { xl: 6, lg: 8, md: 12 },
  },
  {
    field: 'name',
    label: '姓名',
    component: 'Input',
    colProps: { xl: 6, lg: 8, md: 12 },
  },
  {
    field: 'gender',
    label: '性别',
    component: 'Select',
    componentProps: {
      options: [
        { label: '男', value: 'male' },
        { label: '女', value: 'female' },
      ],
      placeholder: '请选择性别',
    },
    colProps: { xl: 6, lg: 8, md: 12 },
  },
  {
    field: 'mobile',
    label: '手机号码',
    component: 'Input',
    colProps: { xl: 6, lg: 8, md: 12 },
  },
  {
    field: 'employeeStatus',
    label: '员工状态',
    component: 'Select',
    componentProps: {
      options: [
        { label: '在职', value: 'active' },
        { label: '离职', value: 'inactive' },
        { label: '试用', value: 'trial' },
        { label: '退休', value: 'retired' },
      ],
      placeholder: '请选择员工状态',
    },
    colProps: { xl: 6, lg: 8, md: 12 },
  },
  {
    field: 'education',
    label: '学历',
    component: 'Select',
    componentProps: {
      options: [
        { label: '博士', value: 'doctor' },
        { label: '硕士', value: 'master' },
        { label: '本科', value: 'bachelor' },
        { label: '专科', value: 'college' },
        { label: '高中', value: 'high_school' },
        { label: '其他', value: 'other' },
      ],
      placeholder: '请选择学历',
    },
    colProps: { xl: 6, lg: 8, md: 12 },
  },
  {
    field: 'position',
    label: '职位',
    component: 'Input',
    colProps: { xl: 6, lg: 8, md: 12 },
  },
  {
    field: 'hireDateRange',
    label: '入职日期',
    component: 'RangePicker',
    componentProps: {
      format: 'YYYY-MM-DD',
      valueFormat: 'YYYY-MM-DD',
      placeholder: ['开始日期', '结束日期'],
    },
    colProps: { xl: 6, lg: 8, md: 12 },
  },
];

// 新增/编辑表单配置
export const formSchema: FormSchema[] = [
  {
    field: 'employeeNo',
    label: '员工工号',
    component: 'Input',
    required: true,
    rules: [
      { required: true, message: '请输入员工工号' },
      { min: 2, max: 32, message: '员工工号长度必须在2-32字符之间' },
    ],
  },
  {
    field: 'name',
    label: '姓名',
    component: 'Input',
    required: true,
    rules: [
      { required: true, message: '请输入姓名' },
      { min: 2, max: 50, message: '姓名长度必须在2-50字符之间' },
    ],
  },
  {
    field: 'gender',
    label: '性别',
    component: 'Select',
    componentProps: {
      options: [
        { label: '男', value: 'male' },
        { label: '女', value: 'female' },
      ],
      placeholder: '请选择性别',
    },
  },
  {
    field: 'birthDate',
    label: '出生日期',
    component: 'DatePicker',
    componentProps: {
      format: 'YYYY-MM-DD',
      valueFormat: 'YYYY-MM-DD',
      placeholder: '请选择出生日期',
    },
  },
  {
    field: 'idCard',
    label: '身份证号',
    component: 'Input',
    componentProps: {
      placeholder: '请输入身份证号',
      maxlength: 18,
    },
  },
  {
    field: 'mobile',
    label: '手机号码',
    component: 'Input',
    componentProps: {
      placeholder: '请输入手机号码',
      maxlength: 11,
    },
  },
  {
    field: 'email',
    label: '邮箱',
    component: 'Input',
    componentProps: {
      placeholder: '请输入邮箱',
    },
  },
  {
    field: 'position',
    label: '职位',
    component: 'Input',
    componentProps: {
      placeholder: '请输入职位',
    },
  },
  {
    field: 'hireDate',
    label: '入职日期',
    component: 'DatePicker',
    componentProps: {
      format: 'YYYY-MM-DD',
      valueFormat: 'YYYY-MM-DD',
      placeholder: '请选择入职日期',
    },
  },
  {
    field: 'employeeStatus',
    label: '员工状态',
    component: 'Select',
    componentProps: {
      options: [
        { label: '在职', value: 'active' },
        { label: '离职', value: 'inactive' },
        { label: '试用', value: 'trial' },
        { label: '退休', value: 'retired' },
      ],
      placeholder: '请选择员工状态',
    },
    defaultValue: 'active',
  },
  {
    field: 'education',
    label: '学历',
    component: 'Select',
    componentProps: {
      options: [
        { label: '博士', value: 'doctor' },
        { label: '硕士', value: 'master' },
        { label: '本科', value: 'bachelor' },
        { label: '专科', value: 'college' },
        { label: '高中', value: 'high_school' },
        { label: '其他', value: 'other' },
      ],
      placeholder: '请选择学历',
    },
  },
  {
    field: 'address',
    label: '联系地址',
    component: 'InputTextArea',
    componentProps: {
      placeholder: '请输入联系地址',
      rows: 3,
      maxlength: 200,
      showCount: true,
    },
  },
  {
    field: 'emergencyContact',
    label: '紧急联系人',
    component: 'Input',
    componentProps: {
      placeholder: '请输入紧急联系人',
    },
  },
  {
    field: 'emergencyPhone',
    label: '紧急联系电话',
    component: 'Input',
    componentProps: {
      placeholder: '请输入紧急联系电话',
      maxlength: 11,
    },
  },
  {
    field: 'remark',
    label: '备注',
    component: 'InputTextArea',
    componentProps: {
      placeholder: '请输入备注信息',
      rows: 4,
      maxlength: 500,
      showCount: true,
    },
  },
];