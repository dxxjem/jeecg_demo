<template>
  <BasicModal
    v-bind="$attrs"
    :title="modalTitle"
    :width="800"
    @register="registerModal"
    @ok="handleSubmit"
  >
    <BasicForm @register="registerForm" />
  </BasicModal>
</template>

<script lang="ts" setup>
import { ref, computed, unref } from 'vue';
import { BasicModal, useModalInner } from '/@/components/Modal';
import { BasicForm, useForm } from '/@/components/Form';
import { useMessage } from '/@/hooks/web/useMessage';
import {
  addEmployee,
  editEmployee,
  getEmployeeById,
} from '../api/employee';
import { formSchema } from '../employee.data';

const emit = defineEmits(['success', 'register']);
const { createMessage } = useMessage();

const isUpdate = ref(true);
const rowId = ref('');

const modalTitle = computed(() => (!unref(isUpdate) ? '新增员工' : '编辑员工'));

const [registerForm, { setFieldsValue, resetFields, validate }] = useForm({
  labelWidth: 100,
  schemas: formSchema,
  showActionButtonGroup: false,
  baseColProps: { lg: 24, md: 24 },
});

const [registerModal, { setModalProps, closeModal }] = useModalInner(async (data) => {
  // 重置表单
  await resetFields();
  setModalProps({ confirmLoading: false });
  isUpdate.value = !!data?.isUpdate;

  if (unref(isUpdate)) {
    // 编辑模式，获取详情数据
    rowId.value = data.record.id;
    try {
      const res = await getEmployeeById(data.record.id);
      if (res.success && res.result) {
        await setFieldsValue({
          ...res.result,
        });
      }
    } catch (error) {
      createMessage.error('获取员工详情失败');
    }
  }
});

// 提交表单
const handleSubmit = async () => {
  try {
    const values = await validate();
    setModalProps({ confirmLoading: true });

    if (unref(isUpdate)) {
      // 编辑
      const res = await editEmployee({ ...values, id: rowId.value });
      if (res.success) {
        createMessage.success('编辑成功');
        closeModal();
        emit('success');
      } else {
        createMessage.error(res.message || '编辑失败');
      }
    } else {
      // 新增
      const res = await addEmployee(values);
      if (res.success) {
        createMessage.success('新增成功');
        closeModal();
        emit('success');
      } else {
        createMessage.error(res.message || '新增失败');
      }
    }
  } catch (error) {
    createMessage.error('操作失败');
  } finally {
    setModalProps({ confirmLoading: false });
  }
};
</script>